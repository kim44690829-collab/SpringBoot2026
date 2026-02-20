import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


export default function Product(){

    // 모든 값을 하나의 객체로 관리하기
    const [car, setCar] = useState({
        carName:'',
        price:'',
        company:'',
        info:'',
        img:null,
    })

    const navigate = useNavigate();

    // 상품 등록하는 함수
    const handleSubmit = () => {

        // React에서 이미지 업로드시 반드시 formData 객체를 생성한다.
        const formData = new FormData();

        // 자바의 확장 for문과 비슷한 react의 for ~ in 문
        // 객체의 key를 하나씩 꺼내는 구문
        for(let key in car){
            // key중 img가 있는지 확인
            if(key === 'img'){
                formData.append('uploadFile', car[key]);
            }else if(key === 'price'){
                formData.append(key, Number(car[key]));
            }else{
                formData.append(key, car[key])
            }
        }

        axios.post("/api/cars/insert", formData)
        .then((res) => {
            if(res.data === 1){
                alert('상품등록 성공');
                navigate('/');
            }
        })
        .catch((err) => {
            console.log(err);
            alert('등록 실패')
        })
    }

    // 공통 입력 처리 함수
    const handleChange = (e) => {
        // input의 name값 가져오기
        const inputName = e.target.name;

        if(e.target.type === 'file'){
            // car를 반드시 얕은복사 => 리랜더링을 위함
            setCar({...car, [inputName]:e.target.files[0]});
        }else{
            // file을 제외한 모든 숫자,문자의 input 값
            setCar({...car, [inputName]:e.target.value});
        }
    }

    return (
    <div id="section_wrap">
      <div className="word">상품등록</div>
      <table width="500" border="1">
        <tbody>
          <tr>
            <td>자동차이름</td>
            <td>
              <input type="text" name="carName" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>자동차가격</td>
            <td>
              <input type="number" name="price" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>제조사</td>
            <td>
              <input type="text" name="company" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>자동차 이미지</td>
            <td>
              <input type="file" name="img" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td>자동차 정보</td>
            <td>
              <input type="text" name="info" onChange={handleChange} />
            </td>
          </tr>
          <tr>
            <td colSpan="2" align="center">
              <button type="button" onClick={handleSubmit}>
                상품등록
              </button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );

}