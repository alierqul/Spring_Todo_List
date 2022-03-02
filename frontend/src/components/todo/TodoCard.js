import * as React from 'react';
import {formatDate} from '../../utils/Utils';
import {useTranslation} from 'react-i18next';
import ButtonWithProgress from '../ButtonWithProgress';

const TodoCard = (props) => {
    const {singleTodo,onClickDeleteButton, onClickDoneButton, pendingApiCall} =props;
    const {id, todo, startDate, finishDate} =singleTodo;
   const {t}=useTranslation();
  
  
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
                    <p><small>{formatDate(startDate)} {finishDate>0 && ` / ${formatDate(finishDate)}` }</small></p>               
                </div>
         <div className='col-md-4'>
                   
            <ButtonWithProgress className='btn btn-warning' name ={id} text= {t('Done')} onClick={onClickDoneButton} disabled={pendingApiCall} pendingApiCall={pendingApiCall}/>
            <ButtonWithProgress className='btn btn-danger' name ={id} text= {t('Delete')} onClick={onClickDeleteButton} disabled={pendingApiCall} pendingApiCall={pendingApiCall}/>
         </div>        
         </div>  
        </label> 
        </li>  
                                                   
           
    );
};

export default TodoCard;

//react-dom.development.js:13231 Uncaught Error: Objects are not valid as a React child (found: object with keys {id, todo, startDate, finishDate}). If you meant to render a collection of children, use an array instead.