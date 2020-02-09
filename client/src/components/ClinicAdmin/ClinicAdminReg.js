import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Form, Button, Spinner } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { clinicsDataSelector, isFetchClinicsDataSelector } from '../../store/clinics/selectors';
import { regClinicAdmin, fetchClinicsData } from '../../store/clinics/actions';

const ClinicAdminReg = () => {
    const dispatch = useDispatch();
    const [email, setEmail] = useState();
    const [password, setPassword] = useState();
    const [firstName, setFirstName] = useState();
    const [lastName, setLastName] = useState();
    const [clinicId, setClinicId] = useState();
    const [validated, setValidated] = useState(false);
    const clinics = useSelector(clinicsDataSelector);
    const isFetchClinics = useSelector(isFetchClinicsDataSelector);

    useEffect(() => {
        dispatch(
            fetchClinicsData({})
        );
    }, []);

    const handleRegClinicAdmin = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                regClinicAdmin({
                    clinicId,
                    email,
                    password,
                    firstName,
                    lastName
                })
            );
            setValidated(false);
        }
    };

    if (!isFetchClinics) {
        return <div className="d-flex justify-content-center">
            <Spinner animation="border" role="status">
                <span className="sr-only">Loading...</span>
            </Spinner>
        </div>;
    }

    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Registrating clinic admin</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Form noValidate validated={validated} onSubmit={handleRegClinicAdmin}>
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
                            <Form.Group as={Col} md="6">
                                <Form.Label>Last name:</Form.Label>
                                <Form.Control required type="text" placeholder="Enter admin last name"
                                    onChange={({ currentTarget }) => {
                                        setLastName(currentTarget.value);
                                    }}
                                />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} md="6">
                                <Form.Label>Clinic:</Form.Label>
                                <Form.Control required as="select" onChange={({ currentTarget }) => {
                                    setClinicId(currentTarget.value);
                                }} >
                                    <option></option>
                                    {
                                        clinics.map((clinic) => {
                                            return (
                                                <option key={clinic.id} value={clinic.id}>{clinic.name}</option>
                                            );
                                        })
                                    }
                                </Form.Control>
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

export default ClinicAdminReg;