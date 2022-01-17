import React from 'react';

const TodoSingleListItem = (props) => {
    const {singleTodo} =props;
    const {id, todo, startDate, finishDate} =singleTodo;
   
    return (    
        <div className='pl-2'>
        {id} / {todo} / {startDate}
    </div>                                                    
            
    );
};

export default TodoSingleListItem;

//react-dom.development.js:13231 Uncaught Error: Objects are not valid as a React child (found: object with keys {id, todo, startDate, finishDate}). If you meant to render a collection of children, use an array instead.