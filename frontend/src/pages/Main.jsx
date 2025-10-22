import React from 'react';
import { Outlet } from 'react-router-dom';
import Gnb from '@components/layout/Gnb';

const Main = () => {
    return (
        <div className='main-bg'>
            <div className='main'>
                {/* <Gnb /> */}
                <Outlet />
            </div>
        </div>
    );
};

export default Main;