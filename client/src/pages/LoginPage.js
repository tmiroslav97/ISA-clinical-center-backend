import React, { useState, useEffect } from 'react';
import { history } from '../index';
import { Form, Button, Container, Row, Col } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux';
import { loginUser } from '../store/user/actions';
import { userIdSelector } from '../store/user/selectors';

const LoginPage = () => {
    const dispatch = useDispatch();
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [validated, setValidated] = useState(false);
    const userId = useSelector(userIdSelector);

    useEffect(() => {
        if (userId != null && userId != '') {
            history.push('/');
        }
    }, []);


    const handleLogin = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                loginUser({
                    username,
                    password
                })
            );
            setValidated(false);
        }
    };

    return (
        <Container>
            <Row>
                <Col md={{ span: 3, offset: 4 }} xs={12}>
                    <h2 className="border-bottom">Login</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 4 }} xs={12}>
                    <Form noValidate validated={validated} id="logForm" onSubmit={handleLogin}>
                        <Form.Group >
                            <Form.Label>Email address</Form.Label>
                            <Form.Control required type="email" id="txtEmail" placeholder="Enter email"
                                onChange={({ currentTarget }) => {
                                    setUsername(currentTarget.value);
                                }} />
                        </Form.Group>

                        <Form.Group >
                            <Form.Label>Password</Form.Label>
                            <Form.Control required type="password" id="txtPass" pattern=".{5,25}" placeholder="Password"
                                onChange={({ currentTarget }) => {
                                    setPassword(currentTarget.value);
                                }} />
                            <Form.Control.Feedback type="invalid">
                                min 5 max 25 characters
                                </Form.Control.Feedback>
                        </Form.Group>
                        <Button variant="primary" id="btnLogin" type="submit">
                            Login
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default LoginPage;
