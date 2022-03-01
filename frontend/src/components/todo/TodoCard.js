import * as React from 'react';

const TodoCard = (props) => {
    const {singleTodo,onClickDeleteButton, onClickDoneButton} =props;
    const {id, todo, startDate, finishDate} =singleTodo;
    const today =new Date(startDate);
    const formatingDate =new Intl.DateTimeFormat('tr-TR', {year: 'numeric', month: 'long',day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit'}).format(today);
  
    let textDecarion = "col-md-8";
    if(finishDate>0){
        textDecarion = "col-md-8 text-decoration-line-through";
    }
    
    return (    
       
        <li className='list-group-item'>
             <label className="list-group-item">
             <div className='row'>    
                <div className={textDecarion}>                                    
                    {todo}
                    <p><small>{formatingDate}</small></p>               
                </div>
         <div className='col-md-4'>
            <button className='btn btn-warning' name ={id} onClick={onClickDoneButton} >Yapıldı.</button>
            <button className='btn btn-danger' name={id} onClick={onClickDeleteButton} >Sil</button>
         </div>        
         </div>  
        </label> 
        </li>  
                                                   
           
    );
};

export default TodoCard;

//react-dom.development.js:13231 Uncaught Error: Objects are not valid as a React child (found: object with keys {id, todo, startDate, finishDate}). If you meant to render a collection of children, use an array instead.