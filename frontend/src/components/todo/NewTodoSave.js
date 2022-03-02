import React from 'react';
import {useTranslation} from 'react-i18next';

const NewTodoSave = props => {
    const {onClickSaveTodo,onChangeTodoText, pendingApiCall} =props;
    const {t}=useTranslation();
    return (
        <div className='row m-1 p-3'>
        <div className='col col-11 mx-auto'>
            <div className='row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center'>
                <div className='col'>
                    <input className='form-control form-control-lg border-0 add-todo-input bg-transparent rounded' type='text' placeholder={t("Add new todo ...")} onChange={onChangeTodoText}/>
                </div>
               
                <div className='col-auto px-0 mx-0 mr-2'>
                    <button type='button' className='btn btn-primary' onClick={onClickSaveTodo}>{t("Add")}</button>
                </div>
            </div>
        </div>
    </div>
    
    );
};

export default NewTodoSave;