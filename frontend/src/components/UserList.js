import React, { useEffect, useState } from 'react';
import { getUsers } from '../api/apiCalls';
import {useTranslation} from 'react-i18next';
import UserListItem from './UserListItem';
import { useApiProgress } from '../shared/ApiProgress';


const UserList =props=> {

    const [page,setPage]=useState({
        content:[],
        size:3,
        number:0
    });

    const[loadFailure,setLoadFailure]=useState(false);

    const pendingApiCall=useApiProgress('/api/1.0/users?page');
 
        useEffect( ()=>{
            loadUsers();
        },[]);
     const onClickNext =event =>{
        const nextPage=page.number + 1;
        loadUsers(nextPage);

    }
    const onClickPrevious =event =>{
        const nextPage=page.number - 1;
        loadUsers(nextPage);

    }

    const loadUsers=async page =>{
        setLoadFailure(false);
        try{
         const response=await getUsers(page);
         setPage(response.data);
        }catch(error){
            setLoadFailure(true);
        }
       
    }
    const {content:users,last,first}=page;
        
    const {t} =useTranslation();

    let actionDiv=(
     <div>
        <span> {first=== false && <button className='btn btn-sm btn-light float-start' onClick={onClickPrevious}>{t('Previous')}</button>}</span>                            
         <span>{last=== false && <button className="btn btn-sm btn-light float-end" onClick={onClickNext}>{t('Next')}</button>}</span>
     </div>
    );
    if(pendingApiCall){
        actionDiv=(
            <div class="d-flex justify-content-center">
            <div class="spinner-border" role="status">
                <span class="sr-only"></span>
            </div>
            </div>
        );
    } 
    return (
        <div className='card'>
            <h3 className='card-header text-center' > {t('Users')}</h3>
            <div className='list-group-flush'>
            {
              users.map((user,index)=>(                  
                <UserListItem key={user.username} user={user}/>
              ))                                                                          
            }
            </div>
           {actionDiv}
           {loadFailure && <div className='text-center text-danger'> {t('Load Failure')} </div>}
        </div>
    );
}

export default UserList;