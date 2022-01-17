import React from 'react';
import defaultProfileIcon from '../img/profile.png';
import {Link} from 'react-router-dom';
const UserListItem = (props) => {
    const {user} =props;
    const {username,name,image} =user;
    let imageSource=defaultProfileIcon;
    if(image){
        imageSource=image;
    }
    return (    
                    <Link to={`/user/${username}`} className='list-group-item list-group-item-action' >
                        <img className='rounded-circle me-4'
                         width='32'
                         height='32'

                         alt={`${user.username} profile`} 
                         src={imageSource} 
                         
                         />
                        <span className='pl-2'>
                            {username}@{name}
                        </span>
                    </Link>                                                            
            
    );
};

export default UserListItem;