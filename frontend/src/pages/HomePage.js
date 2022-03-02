import React from 'react';
import UserList from '../components/UserList';
import { useSelector } from "react-redux";
import TodoList from '../components/todo/TodoList';


const HomePage = props => {
  const {isLoggedIn, username} =useSelector(store=>({isLoggedIn:store.isLoggedIn,username:store.username}));
    return (
       
             <div className="container" > 
               
                {
                  isLoggedIn && <> <TodoList username={username}/></>
                }
                 <UserList ></UserList>
              </div>
       
    );
};

export default HomePage;