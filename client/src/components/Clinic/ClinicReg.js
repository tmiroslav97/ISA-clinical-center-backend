import React, { useState } from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { regClinic } from '../../store/clinics/actions';
import ClinicPagination from './ClinicPagination';


const ClinicReg = () => {
    const dispatch = useDispatch();
    const [name, setName] = useState();
    const [description, setDescription] = useState();
    const [address, setAddress] = useState();
    const [validated, setValidated] = useState(false);

    const handleClinicReg = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                regClinic({
                    name,
                    description,
                    address
                })
            );
            setValidated(false);
        }
    };


    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Register clinic</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 5, offset: 1 }} xs={12}>
                    <Form noValidate validated={validated} onSubmit={handleClinicReg}>
                        <Form.Group as={Col}>
                            <Form.Label>Clinic name:</Form.Label>
                            <Form.Control required type="text" pattern=".{5,50}"  placeholder="Enter clinic name"
                                onChange={({ currentTarget }) => {
                                    setName(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Clinic description:</Form.Label>
                            <Form.Control required as="textarea" pattern=".{5,90}" rows="4" placeholder="Enter clinic description"
                                onChange={({ currentTarget }) => {
                                    setDescription(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Clinic address:</Form.Label>
                            <Form.Control required type="text" pattern=".{5,60}"  placeholder="Enter clinic address"
                                onChange={({ currentTarget }) => {
                                    setAddress(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Button variant="primary" type="submit">
                                Register
                            </Button>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <ClinicPagination />
        </Container>
    );
}

export default ClinicReg;