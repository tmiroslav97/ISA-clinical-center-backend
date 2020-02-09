import React, { useState } from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { addMedicine } from '../../store/medicine_diagnose/actions';
import PaginationMedicine from './PaginationMedicine';

const AddMedicine = () => {
    const dispatch = useDispatch();
    const [name, setName] = useState();
    const [description, setDescription] = useState();
    const [code, setCode] = useState();    
    const [validated, setValidated] = useState(false);

    const handleAddMedicine = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        if (form.checkValidity() === false) {
            event.stopPropagation();
            setValidated(true);
        } else {
            dispatch(
                addMedicine({
                    code,
                    name,
                    description
                })
            );
            setValidated(false);
        }
    };


    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Add medicine</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 5, offset: 1 }} xs={12}>
                    <Form noValidate validated={validated} onSubmit={handleAddMedicine}>
                        <Form.Group as={Col}>
                            <Form.Label>Code:</Form.Label>
                            <Form.Control required type="text" placeholder="Enter code"
                                onChange={({ currentTarget }) => {
                                    setCode(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Name:</Form.Label>
                            <Form.Control required type="text" placeholder="Enter name"
                                onChange={({ currentTarget }) => {
                                    setName(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Description:</Form.Label>
                            <Form.Control required as="textarea" rows="4" placeholder="Enter description"
                                onChange={({ currentTarget }) => {
                                    setDescription(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Button variant="primary" type="submit">
                                Add medicine
                            </Button>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <PaginationMedicine />
        </Container>
    );
}

export default AddMedicine;