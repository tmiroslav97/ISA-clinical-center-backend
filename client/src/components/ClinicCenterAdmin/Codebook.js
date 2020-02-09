import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Form, Button, Table } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { addCode,  } from '../../store/medicine_diagnose/actions';
import MedicineDiagnose from './MedicineDiagnose';

function Codebook() {
    const dispatch = useDispatch();
    const [name, setName] = useState();
    const [description, setDescription] = useState();
    const [code, setCode] = useState();
    const [type, setType] = useState('Medicine');

    const handleAddMedicine = () => {
        dispatch(
            addCode({
                code,
                name,
                description,
                type
            })
        );
    };


    return (
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">Add code</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 5, offset: 1 }} xs={12}>
                    <Form>
                        <Form.Group as={Col}>
                            <Form.Label>Code:</Form.Label>
                            <Form.Control type="text" placeholder="Enter code"
                                onChange={({ currentTarget }) => {
                                    setCode(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Name:</Form.Label>
                            <Form.Control type="text" placeholder="Enter name"
                                onChange={({ currentTarget }) => {
                                    setName(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Description:</Form.Label>
                            <Form.Control type="textarea" placeholder="Enter description"
                                onChange={({ currentTarget }) => {
                                    setDescription(currentTarget.value);
                                }}
                            />
                        </Form.Group>
                        <Form.Group as={Col}>
                            <div align="center">
                                <Form.Label>Type of code</Form.Label>
                            </div>
                            <Form.Control as="select" onChange={({ currentTarget }) => {
                                setType(currentTarget.value);
                            }} >
                                <option key="0" value="Medicine">Medicine</option>
                                <option key="1" value="Diagnose">Diagnose</option>

                            </Form.Control>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Button variant="primary" onClick={handleAddMedicine}>
                                Add medicine
                            </Button>
                        </Form.Group>
                    </Form>
                </Col>
            </Row>
            <MedicineDiagnose/>
        </Container>
    );
}

export default Codebook;