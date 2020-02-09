import React, { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { signOut } from '../store/user/actions';

const Unauthorized = () => {
    const dispatch = useDispatch();

    useEffect(() => {
        setTimeout(() => {
            dispatch(
                signOut({
                    'id': null,
                    'firstName': '',
                    'lastName': '',
                    'email': '',
                    'firstLog': true,
                    'roles': []
                })
            );
        }, 5000);
    }, []);

    return (
        <div>
            <h2>You are not authorized ERROR 401 please login!</h2>
            <p>You will be redirected to home page in 5 seconds...</p>
        </div>
    );
}

export default Unauthorized;