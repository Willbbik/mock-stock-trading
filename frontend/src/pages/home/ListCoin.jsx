import api from "@api/api";
import { Client } from "@stomp/stompjs";
import { useState } from "react";
import { useEffect } from "react";
import SockJS from 'sockjs-client/dist/sockjs';
import useCoinStore from '../../utils/store';

const ListCoin = () => {

    const { market, price, setMarket, setPrice } = useCoinStore();
    const [activeTab, setActiveTab] = useState("krw");
    const [markets, setMarkets] = useState([])

    // 버튼 메뉴
    const tabs = [
        { key: "krw", label: "원화" },
        { key: "btc", label: "BTC" },
        { key: "usdt", label: "USDT" },
    ]

    // 코인 목록 조회
    const getMarkets = async (market) => {
        const param = {
            market: market
        }

        const res = await api.get("/upbit/markets", {
            params: param
        })

        setMarkets(res.data)
    }

    // 웹소켓으로 받아온 정보 업데이트
    const updateMarket = (marketKey, newPrice) => {
        setMarkets(prevMarkets =>
          prevMarkets.map(m =>
            m.market === marketKey
              ? { ...m, tradePrice: newPrice } // 해당 객체만 교체
              : m
          )
        );
    };

    // 차트 상세 조회
    const showCoinDetail = (market) => {
        setMarket(market.market)
        setPrice(market.tradePrice)
    }

    useEffect(() => {
        getMarkets("krw")

        const socket = new SockJS('http://localhost:8080/ws');

        const client = new Client({
            webSocketFactory: () => socket,
            onConnect: () => {
                client.subscribe('/topic/test01', message => {
                    const data = JSON.parse(JSON.parse(message.body)); 
                    updateMarket(data.code, data.tradePrice)
                });
                // client.publish({ destination: '/topic/test01', body: 'First Message' });
              },
        })
    
        client.activate();

        return () => {
            client.deactivate();
        };
    }, [])

    useEffect(() => {
        getMarkets(activeTab)
    }, [activeTab])

    return (
        <section className="market-container">
            <ul className="market-container__tabs">
                {
                    tabs.map((tab) => (
                        <li key={tab.key}>
                            <button
                                className={`market-container__tabs__button ${tab.key === activeTab ? "active" : ""}`}
                                type="button" onClick={() => setActiveTab(tab.key)}>
                                {tab.label}
                            </button>
                        </li>
                    ))
                }
            </ul>
            <div>
                <table className="market-container__table">
                    <thead>
                        <tr>
                            <th>한글명</th>
                            <th>현재가</th>
                            <th>전일대비</th>
                            {/* <th>매수</th> */}
                        </tr>
                    </thead>
                    <tbody>
                        {
                            markets.map((market) => (
                                <tr key={market.market}>
                                    <td className="market-btn">
                                        <a 
                                            href="#"
                                            onClick={(e) => {
                                            e.preventDefault();
                                            showCoinDetail(market)}}
                                        >
                                            {market.market}</a>
                                    </td>
                                    <td>{market.tradePrice}</td>
                                    <td>{market.changePrice}</td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </section>
    );
};

export default ListCoin;