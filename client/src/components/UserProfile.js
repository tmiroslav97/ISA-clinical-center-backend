import React, { useState, useEffect } from 'react';
import { Row, Col, Container, Table, Button, Modal, Form } from 'react-bootstrap';
import { userDataSelector } from '../store/user/selectors';
import { useSelector,useDispatch } from 'react-redux';
import {editUserInformation} from '../store/user/actions';
 
 
const UserProfile = () => {
    const dispatch = useDispatch();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [id, setId] = useState(0);
 
   
    const data = useSelector(userDataSelector);
   
    const [show1, setShow1] = useState(false);
 
    const handleShowEdit = (data) => {
        setFirstName(data.firstName);
        setLastName(data.lastName);
        setId(data.id);
        setShow1(true);
    }
    const handleEdit = () =>{
        dispatch(
            editUserInformation({id,firstName,lastName})
        );
        setShow1(false);
    }
   
   
    return (
        <>
        <Modal show={show1}  onHide={handleEdit} animation={false}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit data:</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group as={Col}>
                            <Form.Label>First name:</Form.Label>
                            <Form.Control type="text"  value={firstName} onChange={({ currentTarget }) => {
                                setFirstName(currentTarget.value);}}/>
                        </Form.Group>
                        <Form.Group as={Col}>
                            <Form.Label>Last name:</Form.Label>
                            <Form.Control type="text" value={lastName} onChange={({ currentTarget }) => {
                                setLastName(currentTarget.value);}} />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handleEdit}>
                        Edit
                    </Button>
                </Modal.Footer>
        </Modal>
 
 
        <Container>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <h2 className="border-bottom">User profile</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 10, offset: 1 }} xs={12}>
                    <Table responsive>
                        <tbody>
                            <tr>
                                <th>First name</th>
                                <td align="right">{data.firstName}</td>
                            </tr>
                            <tr>
                                <th>Last name</th>
                                <td align="right">{data.lastName}</td>
                            </tr>
                            <tr>
                                <th>Email</th>
                                <td align="right">{data.email}</td>
                            </tr>
                            <tr>
                                <th>Password</th>
                                <td align="right">
                                    <a href="/change-pass">Change password</a>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2" align="right">
                                    <Button onClick={()=>{handleShowEdit(data)}}>Edit data</Button>
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
        </>
    );
}
 
export default UserProfile;