import React, { useState, useEffect } from 'react';
import { history } from '../../index';
import { Container, Form, Button, Row, Col } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { regCCAdmin } from '../../store/user/actions';
import { userIdSelector, userDataSelector, isFetchUserDataSelector } from '../../store/user/selectors';

const AdminReg = () => {
    const dispatch = useDispatch();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();
    const ccaId = useSelector(userIdSelector);
    const userData = useSelector(userDataSelector);
    const isFetchUserData = useSelector(isFetchUserDataSelector);
    const [validated, setValidated] = useState(false);


    useEffect(() => {
        if (isFetchUserData && !userData.predefined) {
            history.push('/ccadmin');
        }
    }, [ccaId]);

    const handleRegCCAdmin = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                regCCAdmin({
                    ccaId,
                    email,
                    password,
                    firstName,
                    lastName
                })
            );
            setValidated(false);
        }
    };

    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Registrating new clinic center admin</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Form noValidate validated={validated} onSubmit={handleRegCCAdmin}>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>Admin e-mail:</Form.Label>
                                <Form.Control required type="email" placeholder="Enter admin e-mail"
                                    onChange={({ currentTarget }) => {
                                        setEmail(currentTarget.value);
                                    }}
                                />
                            </Form.Group>
                            <Form.Group as={Col}>
                                <Form.Label>Admin password:</Form.Label>
                                <Form.Control required type="password" pattern=".{5,25}" placeholder="Enter admin password"
                                    onChange={({ currentTarget }) => {
                                        setPassword(currentTarget.value);
                                    }}
                                />
                                <Form.Control.Feedback type="invalid">
                                    min 5 max 25 characters
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <Form.Label>First name:</Form.Label>
                                <Form.Control required type="text" placeholder="Enter admin first name"
                                    onChange={({ currentTarget }) => {
                                        setFirstName(currentTarget.value);
                                    }}
                                />
                            </Form.Group>
                            <Form.Group as={Col}>
                                <Form.Label>Last name:</Form.Label>
                                <Form.Control required type="text" placeholder="Enter admin last name"
                                    onChange={({ currentTarget }) => {
                                        setLastName(currentTarget.value);
                                    }}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Button variant="primary" type="submit">
                            Register
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default AdminReg;