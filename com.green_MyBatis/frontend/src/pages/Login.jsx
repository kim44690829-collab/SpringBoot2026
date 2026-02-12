import { useState, useContext } from 'react';
import { AuthContext } from '../contexts/AuthContext';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
// import './Member.css';

export default function Login() {

    const {login} = useContext(AuthContext);

    const navigate = useNavigate();

    const [id, setId] = useState('');
    const [pw, setPw] = useState('');

    // 로그인 버튼 클릭 실행핸들러
    const loginConfirm = () => {
        if(id === ''){
            alert("아이디를 입력해주세요.");
            return;
        }
        if(pw === ''){
            alert("비밀번호를 입력해주세요.");
            return;
        }

        axios.post('/api/member/login', {id:id, pw:pw})
        .then((res) => {
            // 로그인 성공과 실패를 if문으로 확인
            if(res.data){
                alert(`${res.data.id}님 환영합니다.`);
                // AuthContext login 함수 호출
                login(res.data.id);
                navigate('/');
            }else{
                alert('로그인 실패, 아이디 혹은 비밀번호를 다시 확인해주세요');
                navigate('/member/signup');
            }
            
        })
        .catch((error) => {
            console.error(error);
        })
        
    }
 

  return (
    <div id="section_wrap">
      <div className="word">로그인</div>

      <table width="500" border="1">
        <tbody>
          <tr>
            <td>아이디</td>
            <td>
              <input type="text" name="id" onChange={(e) => setId(e.target.value)} />
            </td>
          </tr>

          <tr>
            <td>비밀번호</td>
            <td>
              <input type="password" name="pw" onChange={(e) => setPw(e.target.value)} />
            </td>
          </tr>

          <tr>
            <td colSpan="2" align="center">
              <button onClick={loginConfirm}>로그인</button>
              <button type="reset">취소</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}