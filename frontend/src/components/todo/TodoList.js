import React , { useEffect, useState } from 'react';
import { getMyTodoList,saveTodo } from '../../api/apiCalls';
import TodoCard from './TodoCard';
import NewTodoSave from './NewTodoSave';
import {useSelector} from 'react-redux';
import {deleteTodo,doneTodo} from '../../api/apiCalls';
import {useTranslation} from 'react-i18next';
import { useApiProgress } from '../../shared/ApiProgress';

const TodoList = props => {
  
    
    const {t}=useTranslation();
    const [page,setPage]=useState({
        content:[],
        size:3,
        number:0
    });
    const[todo,setTodo]=useState();
    const {loggedInUsername}=useSelector(store=>({loggedInUsername:store.username}));
    const pendingApiCall = useApiProgress(`/api/1.0/${loggedInUsername}/todo`);
    
    useEffect( ()=>{
        loadTodoList();
    },[]);

    

    const loadTodoList=async page =>{
       
        try{
         const response=await getMyTodoList(loggedInUsername,page);
         setPage(response.data);
        }catch(error){
           
        }
       
    };   

    const onClickDeleteButton = async (event) =>{
        
        const body={
            todoID:event.target.name
        };
       
      await deleteTodo(loggedInUsername,body);
      loadTodoList();
     
    };

    const onClickDoneButton = async (event) =>{
        
        const body={
            todoID:event.target.name
        };
       console.log("body = "+ body);
      await doneTodo(loggedInUsername,body);
      loadTodoList();
     
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
        await saveTodo(loggedInUsername,body);
        loadTodoList();
    };


    const {content:todos}=page;
    return (
        <div className='container m-5 p-2 rounded mx-auto bg-light shadow'>
            <NewTodoSave onChangeTodoText={loadChangeTodoText} onClickSaveTodo={onClickSaveTodo} pendingApiCall={pendingApiCall} ></NewTodoSave>
        <div className='row m-1 p-4'>
        <div className='col'>
            <div className='p-1 h1 text-primary text-center mx-auto display-inline-block'>
                <i className='fa fa-check bg-primary text-white rounded p-2'></i>
                <u>{t("My Todo-s")}</u>
            </div>
        </div>
    </div>
  
  
    
    <div className='card'>
                        
            <ul className='list-group-flush'>
            {
              todos.map((todo,index)=>(                  
                <TodoCard key={todo.id} singleTodo={todo} 
                onClickDoneButton={onClickDoneButton}
                onClickDeleteButton={onClickDeleteButton}
                pendingApiCall={pendingApiCall}
                />
              ))                                                                          
            } 
            </ul>
         
        </div>
        

    
    </div>
    );
};

export default TodoList;