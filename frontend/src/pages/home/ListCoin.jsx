import api from "@api/api";
import { Client } from "@stomp/stompjs";
import { useState } from "react";
import { useEffect } from "react";
import SockJS from 'sockjs-client/dist/sockjs';
import useCoinStore from '../../utils/store';

const ListCoin = () => {

    const { market, price, setMarket, setPrice } = useCoinStore();
    const [stocks, setStocks] = useState([])

    // 주식 목록 조회
    const getStocks = async () => {
        const res = await api.get("/api/stocks")

        if(res.status === 200 && res.data) {
            setStocks(res.data)
        }
    }

    // 웹소켓으로 받아온 정보 업데이트
    const updateStock = (stockCode, newPrice) => {
        setStocks(prevStocks =>
            prevStocks.map(m =>
                m.stockCode === stockCode
                  ? { ...m, price: newPrice } // 해당 객체만 교체
                  : m
            ));


        // setStocks(prevStocks =>
        //     m.stockCode === stockCode
        //       ? { ...m, price: newPrice } // 해당 객체만 교체
        //       : m
        //   );
    };

    useEffect(() => {
        getStocks()

        const socket = new SockJS('http://localhost:8080/ws');
        const client = new Client({
            webSocketFactory: () => socket,
            onConnect: () => {
                client.subscribe('/topic/test01', message => {
                    const body = JSON.parse(message.body);
                    const { stockCode, data } = body;
                // console.log(stockCode, prdatice, body);
                    updateStock(stockCode, data.price);

                    // const data = JSON.parse(JSON.parse(message.body)); 
                    // updateStock(data.code, data.price)
                });
                // client.publish({ destination: '/topic/test01', body: 'First Message' });
              },
        })
    
        client.activate();

        return () => {
            client.deactivate();
        };
    }, [])

    return (
        <section className="market-container">
            <div>
                <table className="market-container__table">
                    <thead>
                        <tr>
                            <th>한글명</th>
                            <th>현재가</th>
                            <th>전일대비</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            stocks?.map((stock) => (
                                <tr key={stock.stockCode}>
                                    <td className="market-btn">
                                        <a 
                                            href="#"
                                            onClick={(e) => {
                                            e.preventDefault();
                                            showCoinDetail(market)}}
                                        >
                                            {market.market}</a>
                                    </td>
                                    <td>{stock.name}</td>
                                    <td>{stock.price}</td>
                                </tr>
                            ))
                        }

                        {/* {
                            markets?.map((market) => (
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
                        } */}
                    </tbody>
                </table>
            </div>
        </section>
    );
};

export default ListCoin;