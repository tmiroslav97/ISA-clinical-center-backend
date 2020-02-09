import React, { useState } from 'react';
import { Container, Form, Button, Row, Col } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { changePassword } from '../store/user/actions';


const PasswordChanger = () => {
    const dispatch = useDispatch();
    const [oldPassword, setOldPassword] = useState();
    const [newPassword, setNewPassword] = useState();
    const [confNewPassword, setConfNewPassword] = useState();
    const [msg, setMsg] = useState('');
    const [validated, setValidated] = useState(false);

    const handleChange = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setMsg('min 5 max 25 characters');
            setValidated(true);
        } else if (newPassword !== confNewPassword) {
            event.stopPropagation();
            setMsg('Password doesn\'tmatch');
            setValidated(true);
        } else {
            setMsg('');
            dispatch(
                changePassword({
                    oldPassword,
                    newPassword,
                    confNewPassword
                })
            );
        }
    };

    return (
        <Container>
            <Row>
                <Col md={{ span: 5, offset: 3 }} xs={12}>
                    <h1 align="center" className="border-bottom">Change password</h1>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 4 }} xs={12}>
                    <Form noValidate validated={validated} onSubmit={handleChange}>
                        <Form.Group as={Col} controlId="formOld">
                            <div align="center">
                                <Form.Label>Old password:</Form.Label>
                            </div>
                            <Form.Control required type="password" pattern=".{5,25}" placeholder="Enter old password"
                                onChange={({ currentTarget }) => {
                                    setOldPassword(currentTarget.value);
                                }} />
                            <Form.Control.Feedback type="invalid">
                                min 5 max 25 characters
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group as={Col} controlId="formNew">
                            <div align="center">
                                <Form.Label>New password:</Form.Label>
                            </div>
                            <Form.Control required type="password" pattern=".{5,25}" placeholder="Enter new password"
                                onChange={({ currentTarget }) => {
                                    setNewPassword(currentTarget.value);
                                }} />
                            <Form.Control.Feedback type="invalid">
                                min 5 max 25 characters
                            </Form.Control.Feedback>
                        </Form.Group>
                        <Form.Group as={Col} controlId="formConfNew">
                            <div align="center">
                                <Form.Label>Confirm new password:</Form.Label>
                            </div>
                            <Form.Control required type="password" pattern=".{5,25}" placeholder="Confirm new password"
                                onChange={({ currentTarget }) => {
                                    setConfNewPassword(currentTarget.value);
                                }} />
                            <Form.Control.Feedback type="invalid">
                                {msg}
                            </Form.Control.Feedback>
                        </Form.Group>
                        <div align="center">
                            <Button variant="primary" type="submit">
                                Change
                        </Button>
                        </div>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default PasswordChanger;