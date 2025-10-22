
import useCoinStore from '../../utils/store';
import { useEffect } from 'react';
import { useState } from 'react';
import api from '@api/api';
const MainCoin = () => {

    const { market, price, setMarket, setPrice } = useCoinStore();
    const [quantity, setQuantity] = useState(0)
    const [top3Coins, setTop3Coins] = useState([])

    const buyCoin = async () => {
        const body = {
            market: market,
            buyPrice: price,
            buyQuantity: quantity
        }

        try {
            const response = await api.post("/api/coin/buy", body);

            if(response.status === 200 && response.data === "success") {
                alert("매수가 완료되었습니다.");
            } else {
                alert("매수 실패!");
            }

        } catch (error) {
            console.log(error);
            alert("매수 요청 중 에러가 발생했습니다.");
        }
    }

    const getTop3TrendingCoins = async () => {
        const response = await api.get("/api/coin/top3/trending")

        try {
            if(response.status === 200) {
                setTop3Coins(response.data)
            }
        } catch (error) {

        }
    }

    useEffect(() => {
        getTop3TrendingCoins()
    }, [])


    useEffect(() => {
        if(market) {
            setQuantity(0)
        }
    }, [market])

    return (
        <section className="chart-container">
            <div className="chart-container__chart">
                {market} 차트 영역
                <div>
                    <div>인기 코인 순위</div>
                    <div>
                        <ul>
                            {
                                top3Coins.map((coin, index) => (
                                    <li key={coin.market}>
                                        {index + 1}. {coin.market} ({coin.count}회 매수)
                                    </li>
                                ))
                            }
                        </ul>
                    </div>
                </div>
            </div>
            <div className="chart-container__trade">
                <ul className="tab-box">
                    <li><button type="text">매수</button></li>
                    <li><button type="text">매도</button></li>
                </ul>
                <div className="trade-box">
                    <div className="trade-input">
                        <div className="title">매수가격</div>
                        <div><input type="text" value={price} placeholder="0" onChange={(e) => setPrice(e.target.value)}/></div>
                    </div>
                    <div className="trade-input">
                        <div className="title">주문수량</div>
                        <div><input type="number" value={quantity} placeholder='0' onChange={(e) => setQuantity(e.target.value)}/></div>
                    </div>
                </div>
                <div className="decide-box">
                    <div className="btn">
                        <button type="button" onClick={() => buyCoin()} disabled={quantity <= 0}>매수</button>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default MainCoin;