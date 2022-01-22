import React , { useEffect, useState } from 'react';
import { getMyTodoList,saveTodo } from '../api/apiCalls';
import TodoSingleListItem from './TodoSingleListItem';
import NewTodoSave from '../todo/NewTodoSave';
import {useSelector} from 'react-redux';

const TodoList = props => {
    const username= 'P4ssword';
    const [page,setPage]=useState({
        content:[],
        size:3,
        number:0
    });
    const[todo,setTodo]=useState();
    const {loggedInUsername}=useSelector(store=>({loggedInUsername:store.username}));

    useEffect( ()=>{
        loadTodoList();
    },[]);

    

    const loadTodoList=async page =>{
       
        try{
         const response=await getMyTodoList(username,page);
         setPage(response.data);
        }catch(error){
           
        }
       
    };   

    const loadChangeTodoText=event=>{
        const value=event.target.value;
        setTodo(value);      
    };

    const onClickSaveTodo= async event=>{
        const body={
            todo,
            username:loggedInUsername                     
        };        
        await saveTodo(username,body);
        loadTodoList();
    };


    const {content:todos}=page;
    return (
        <div className='container m-5 p-2 rounded mx-auto bg-light shadow'>
            <NewTodoSave onChangeTodoText={loadChangeTodoText} onClickSaveTodo={onClickSaveTodo}></NewTodoSave>
        <div className='row m-1 p-4'>
        <div className='col'>
            <div className='p-1 h1 text-primary text-center mx-auto display-inline-block'>
                <i className='fa fa-check bg-primary text-white rounded p-2'></i>
                <u>My Todo-s</u>
            </div>
        </div>
    </div>
  
  
    
    <div className='card'>
                        
            <ul className='list-group-flush'>
            {
              todos.map((todo,index)=>(                  
                <TodoSingleListItem key={todo.id} singleTodo={todo}/>
              ))                                                                          
            } 
            </ul>
         
        </div>
        

    
    </div>
    );
};

export default TodoList;