import { useEffect, useState } from "react";
import axios from 'axios';
import './Home.css';

export default function Home(){

    // 상태정의 state 필요
    // carlist는 백엔드 springboot에서 받아온 차량의 목록 데이터를 저장하는 변수이다.
    // setCarlist는 데이터를 받아온 후 화면을 다시 리 랜더링을 위해 업데이트를 하는 역할
    // 초기값은 빈 배열로 설정하여 데이터가 들어오기 전의 에러가 나지 않도록 한다.
    const [carlist, setCarlist] = useState([]);

    useEffect(() => {
        // vite.config.js에서 proxy => /api 를 'http://localhost:8090'로 대체한다고 설정함.
        // 결국 우리가 사용해야하는 http://localhost:8090/api/cars 
        axios.get('/api/cars')
        .then((res) => {
            // res.data는 백엔드(SpringBoot)에서 JSON 형태로 보낸 List<CarProductDTO> 데이터가 담겨있다.
            console.log("받아온 데이터", res.data);
            // 받아온 데이터를 setCarlist state에 저장 -> 다시 랜더링됨
            setCarlist(res.data)
        })
        .catch((error) => {
            // 서버가 꺼져있거나 주소가 틀린경우 실행됨.
            console.error("데이터 로딩 에러 : ", error);
        })

    },[])

    //const infoArray = JSON.parse(carlist.info);

    return(
        <section>
            <div id="section_wrap">
                <div className="word">HOME</div>
                <div className="content">
                    <div className="carList">
                        {carlist.length > 0 ? (
                            // map 으로 반복하여 목록 출력
                            carlist.map((car) => (
                                <>
                                    <div className="carItem" key={car.no}>
                                    <img src={`/img/car/${car.img}`} alt={car.carName} />
                                    <div className="carName">
                                        {car.carName}
                                    </div>
                                    <div className="carPrice">
                                        {Number(car.price).toLocaleString()}
                                    </div>
                                    
                                    </div>
                                </>
                            ))
                        ) : (<p>등록된 차량이 존재하지 않습니다.</p>)}
                    </div>
                </div>
            </div>
        </section>
    )

}