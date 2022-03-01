import React , { useEffect, useState } from 'react';
import { getMyTodoList,saveTodo } from '../../api/apiCalls';
import TodoCard from './TodoCard';
import NewTodoSave from './NewTodoSave';
import {useSelector} from 'react-redux';
import {deleteTodo,doneTodo} from '../../api/apiCalls';
import { useParams } from 'react-router-dom';

const TodoList = props => {
    const routePArams=useParams();
    const pathUserName = routePArams.username;

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
         const response=await getMyTodoList(pathUserName,page);
         setPage(response.data);
        }catch(error){
           
        }
       
    };   

    const onClickDeleteButton = async (event) =>{
        
        const body={
            todoID:event.target.name
        };
       
      await deleteTodo(pathUserName,body);
      loadTodoList();
     
    };

    const onClickDoneButton = async (event) =>{
        
        const body={
            todoID:event.target.name
        };
       console.log("body = "+ body);
      await doneTodo(pathUserName,body);
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
        await saveTodo(pathUserName,body);
        loadTodoList();
    };


    const {content:todos}=page;
    return (
        <div className='container m-5 p-2 rounded mx-auto bg-light shadow'>
            <NewTodoSave onChangeTodoText={loadChangeTodoText} onClickSaveTodo={onClickSaveTodo}  ></NewTodoSave>
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
                <TodoCard key={todo.id} singleTodo={todo} 
                onClickDoneButton={onClickDoneButton}
                onClickDeleteButton={onClickDeleteButton}/>
              ))                                                                          
            } 
            </ul>
         
        </div>
        

    
    </div>
    );
};

export default TodoList;