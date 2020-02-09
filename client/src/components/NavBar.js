import React from 'react';
import { useDispatch } from 'react-redux';
import { Navbar, Nav, } from 'react-bootstrap';
import { userDataSelector } from '../store/user/selectors';
import { useSelector } from 'react-redux';
import { signOut } from '../store/user/actions';
import { history } from '../index';

export default function NavBar() {
    const dispatch = useDispatch();
    const user = useSelector(userDataSelector);
    const roles = user.roles;

    const handleSignOut = () => {
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
    };

    //ne postoji razlik izmedju refresh page i close tab pa ako ovo radi onda prilikom refresha se takodje okine ova funkcija sto nije dobro 
    //window.addEventListener("unload", handleSignOut);

    return (
        <Navbar bg="light" expand="lg">
            <Navbar.Brand href="#" onClick={() => { history.push('/') }}>Clinic Center</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Link href="#" onClick={() => { history.push('/') }}>Home</Nav.Link>
                    {roles.includes('ROLE_CCADMIN') && <Nav.Link href="#" onClick={() => { history.push('/ccadmin') }}>Clinic center admin profile</Nav.Link>}
                    {roles.includes('ROLE_NURSE') && <Nav.Link href="#" onClick={() => { history.push('/nurse-page') }}>Nurse homepage</Nav.Link>}
                    {roles.includes('ROLE_ADMINC') && <Nav.Link href="#" onClick={() => { history.push('/adminc') }}>Clinic admin profile</Nav.Link>}
                    {roles.includes('ROLE_DOCTOR') && <Nav.Link href="#" onClick={() => { history.push('/doc') }}>Doctor homepage</Nav.Link>}
                    {roles.includes('ROLE_PATIENT') && <Nav.Link href="#" onClick={() => { history.push('/pat') }}>Patient homepage</Nav.Link>}
                </Nav>
                <Nav className="ml-auto">
                    {roles.length == 0 && <Nav.Link href="#" onClick={() => { history.push('/login') }}>Login</Nav.Link>}
                    {roles.length == 0 && <Nav.Link href="#" onClick={() => { history.push('/signup') }}>Sign Up</Nav.Link>}
                    {roles.length != 0 && <Nav.Link href="#" onClick={() => handleSignOut()}>Sign out</Nav.Link>}
                </Nav>

            </Navbar.Collapse>
        </Navbar>

    );
}

