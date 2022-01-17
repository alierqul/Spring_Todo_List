import React from 'react';
import icon from '../img/icon.png';
import { Link } from 'react-router-dom';
import{ useTranslation} from 'react-i18next';
import {useDispatch,useSelector} from 'react-redux';
import { logOutSuccess } from '../redux/authAction';


const TopBar = props => {
    const { t }=useTranslation();
  
    const { username, isLoggedIn, name, image } = useSelector(store => ({
        isLoggedIn: store.isLoggedIn,
        username: store.username,
        name: store.displayName,
        image: store.image
      }));

    const dispatch = useDispatch();

    const onLogoutSuccess = () =>{
        
        dispatch(logOutSuccess());
    };
  

       
    let links= (
        <ul className="col me-4 ms-auto">
            <Link className='nav-link' to="/login"><li>{t('Login')}</li></Link>
            <Link className='nav-link' to="/register"> <li>{t('Register')}</li></Link>
        </ul>
    );

    if(isLoggedIn){
        links=(
            <div className="col-auto d-flex align-items-center pr-2">
            <Link className='nav-link' to={`/user/${username}`}>{username}</Link>
            <Link className='nav-link' onClick = {onLogoutSuccess} style={{cursor:'pointer'}}>{t('Logout')}</Link>
            
            </div>
        );
    };
            
        
    
        return(
            
            <div className="container m-3 p-2 rounded mx-auto bg-light shadow">
                <div className="row m-3 p-2 mx-auto">  
                    <div className="col">
                        <Link className="navbar-link" to="/">
                        <img src={icon} width="32" height="32" className="d-inline-block align-top ms-4 me-4" alt="todo icon"/>                   
                        <div className="p-1 h6 text-primary  mx-auto display-inline-block">
                            < i className="fa fa-check bg-primary text-white rounded p-2"></i>
                            <u>To-Do List</u>
                            
                        </div>
                        </Link> 
                                                        
                    </div> 
                    {links}                          
                </div>              
            </div>
           
        );   
   
};



export default TopBar;