import { Link } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';
import { useContext } from 'react';
import './Header.css';

export default function Header(){
    // 전역 저장소에서 user와 logout 가져온다.
    const {user, logout} = useContext(AuthContext);

    return(
        <header>
            <div id = 'top'>
                <h3>MEMBER JOIN</h3>
            </div>
            <div id='header_wrap'>
                <Link to="/">HOME</Link>
                {!user ? (
                    <>
                        <Link to="/member/signup">회원가입</Link>
                        <Link to="/member/login">로그인</Link>
                    </>
                ) : (
                    <>
                        <span style={{fontWeight:'bold', color:'#333'}}>
                            {user === 'admin9867' ? 
                                <>
                                    <span>관리자</span> 
                                    <Link to="/member/list">[회원목록]</Link>
                                    <Link to="/cars/insert">[상품등록]</Link>
                                </>
                                :
                                <span>{user} 님 환영합니다.</span>}
                        </span>
                        <Link to="/" onClick={logout}>로그아웃</Link>
                        <Link to="/member/memberInfo">내정보</Link>
                    </>
                )}
                <Link to="/board/list">게시판</Link>
            </div>
            
        </header>
    )

}