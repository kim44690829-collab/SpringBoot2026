import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../contexts/AuthContext';
import { useContext } from 'react';

export default function MyInfo() {
 
  // logout - 회원 탈퇴시 로그인 상태에서 벗어나야함
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const [ info, setInfo ] = useState('');
  // const [ info, setInfo ] = useState(null);

  // 내 정보 조회
  useEffect(() => {
    axios.get('/api/member/myinfo')
    .then((res) => {
      console.log("받은 데이터 : ", res.data);
      if(!res.data){
        // 현재 내 정보가 없음
        alert('로그인이 필요합니다.');
        navigate('/member/login');
      }else{
        setInfo(res.data);
      }
      // if(user === res.data.id){
        
      // }
    })
    .catch((error) => {
      console.error(error);
    })
  },[])

  // 회원 삭제 핸들러
  const deleteHandler = () => {
      if(!window.confirm("정말 삭제하시겠습니까? 삭제된 데이터는 복구 불가능합니다.")){
        return;
      }
    axios.delete('/api/member/delete')
    .then((res) => {
      if(res.data === 1){
        alert("탈퇴되었습니다.");
        logout();
        navigate('/');
      }else{
        alert("삭제 실패")
      }
    })
    .catch((error) => {
      console.log(error);
    })
  }

  // 만약 info 의 초기값을 null로 줬을때 사용해야함
  // if(!member){
  //   return <div>...로딩중</div>
  // }

  return (
    <section>
      <div id="section_wrap">
        <div className="word">
          <h2>개인 회원 상세 정보</h2>
        </div>

        <div className="content">
          <table border="1">
            <tbody>
              <tr>
                <th>아이디</th>
                <td>{info.id}</td>
              </tr>
              <tr>
                <th>이메일</th>
                <td>{ info.mail }</td>
              </tr>
              <tr>
                <th>전화</th>
                <td>{ info.phone}</td>
              </tr>
              <tr>
                <th>등록일</th>
                <td>{info.reg_date }</td>
              </tr>
            </tbody>
          </table>

          {/* 버튼 영역 */}
          <div className="btn-area" style={{ marginTop: '20px' }}>
            <button className="btn" onClick={() => navigate('/member/modify')}>
              회원수정
            </button>

            <button className="btn btn-danger" onClick={deleteHandler}>
              회원탈퇴
            </button>

            <button className="btn" onClick={() => navigate('/')}>
              홈으로
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}