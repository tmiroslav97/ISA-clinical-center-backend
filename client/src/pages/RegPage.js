import React, { useState } from 'react';
import { Form, Button, Row, Col, Container } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { registerUser } from '../store/user/actions';

const RegPage = () => {
    const dispatch = useDispatch();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [password2, setPassword2] = useState();
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();
    const [address, setAddress] = useState();
    const [country, setCountry] = useState();
    const [city, setCity] = useState();
    const [phoneNum, setPhoneNum] = useState();
    const [unoip, setUnoip] = useState();

    const handleRegister = () => {
        dispatch(
            registerUser({
                email,
                password,
                password2,
                firstName,
                lastName,
                address,
                city,
                country,
                phoneNum,
                unoip
            })
        );
    };

    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h1 className="border-bottom">Registration</h1>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formBasicEmail">
                                <Form.Label>E-mail address</Form.Label>
                                <Form.Control type="email" placeholder="E-mail"
                                    onChange={({ currentTarget }) => {
                                        setEmail(currentTarget.value);
                                    }} />
                                <Form.Text className="text-muted">
                                    We'll never share your email with anyone else.
                                </Form.Text>
                            </Form.Group>
                            <Form.Group as={Col} controlId="formCity">
                                <Form.Label>City</Form.Label>
                                <Form.Control type="text" placeholder="City"
                                    onChange={({ currentTarget }) => { setCity(currentTarget.value); }} />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Password"
                                    onChange={({ currentTarget }) => {
                                        setPassword(currentTarget.value);
                                    }} />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formConfirmPassword">
                                <Form.Label>Confirm password</Form.Label>
                                <Form.Control type="password" placeholder="Confirm your password"
                                    onChange={({ currentTarget }) => {
                                        setPassword2(currentTarget.value);
                                    }} />
                            </Form.Group>

                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formAddressd">
                                <Form.Label>Address</Form.Label>
                                <Form.Control type="text" placeholder="Address"
                                    onChange={({ currentTarget }) => {
                                        setAddress(currentTarget.value);
                                    }} />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formCountry">
                                <Form.Label>Country</Form.Label>
                                <Form.Control type="text" placeholder="Country"
                                    onChange={({ currentTarget }) => {
                                        setCountry(currentTarget.value);
                                    }} />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formFirstName">
                                <Form.Label>First name</Form.Label>
                                <Form.Control type="text" placeholder="First name"
                                    onChange={({ currentTarget }) => {
                                        setFirstName(currentTarget.value);
                                    }} />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formContact">
                                <Form.Label>Contact</Form.Label>
                                <Form.Control type="text" placeholder="Contact"
                                    onChange={({ currentTarget }) => {
                                        setPhoneNum(currentTarget.value);
                                    }} />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formLastName">
                                <Form.Label>Last name</Form.Label>
                                <Form.Control type="text" placeholder="Last name"
                                    onChange={({ currentTarget }) => {
                                        setLastName(currentTarget.value);
                                    }} />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formUNOIP">
                                <Form.Label>Unique number of insured persons</Form.Label>
                                <Form.Control type="text" placeholder="UNOIP"
                                    onChange={({ currentTarget }) => {
                                        setUnoip(currentTarget.value);
                                    }} />
                            </Form.Group>
                        </Form.Row>
                        <Button variant="primary" className="mb-4" onClick={handleRegister}>
                            Sign-up
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default RegPage;