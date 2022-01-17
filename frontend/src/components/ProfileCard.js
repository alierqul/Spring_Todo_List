import React from 'react';
import { useParams } from 'react-router-dom';
import {useSelector} from 'react-redux';
//rsc snippet kısayolu

const ProfileCard = props => {
     const routePArams=useParams();
    const pathUserName = routePArams.username;
   
    const {loggedInUsername}=useSelector(store=>({loggedInUsername:store.username}));
    let message= "We Connot Edit";

    if(pathUserName===loggedInUsername){
        message="we can edit";
    }
   return(    
        <div className="container">      {message}       </div>
   );
  
};

export default ProfileCard;