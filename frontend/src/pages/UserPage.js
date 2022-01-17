import React from 'react';
import ProfileCard from '../components/ProfileCard';
import TodoList from '../todo/TodoList';

const UserPage = props => {
    return (
        <div className="container">
            <ProfileCard/>
            <TodoList/>
        </div>
    );
};

export default UserPage;