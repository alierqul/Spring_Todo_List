import * as React from 'react';

const TodoSingleListItem = (props) => {
    const {singleTodo} =props;
    const {id, todo, startDate, finishDate} =singleTodo;
    const today =Date.parse(startDate.toString())
    const formatingDate =new Intl.DateTimeFormat('tr-TR', {year: 'numeric', month: 'long',day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit'}).format(today);
    
    return (    
        <li className='list-group-item'>
        <label className="list-group-item">
        <input className="form-check-input me-1" type="checkbox" value="" />
         {todo}
        <p><small>{formatingDate}</small></p>

         </label>
         
    </li>                                                    
           
    );
};

export default TodoSingleListItem;

//react-dom.development.js:13231 Uncaught Error: Objects are not valid as a React child (found: object with keys {id, todo, startDate, finishDate}). If you meant to render a collection of children, use an array instead.