import React, {useState} from 'react';
import {Table, Form, Button, Modal, Container,Row} from 'react-bootstrap';
//import RejectedReason from './RejectedReason';

function HolidayAproval(){
    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    return(
        <>
        <Modal show={show} onHide={handleClose} animation={false}>
        <Modal.Header closeButton>
          <Modal.Title>Rejected reason:</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <Form.Control type="text"/>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={handleClose}>
            Sand
          </Button>
        </Modal.Footer>
      </Modal>
        <Container>
            <Row>
                <h2>Aprove or reject the request for holiday</h2>
            </Row>
            <Row>
                <Table responsive>
                    <thead>
                        <tr>
                        <th>#</th>
                        <th>E-mail</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Accept</th>
                        <th>Reject</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <td>1</td>
                        <td>perica@gmail.com</td>
                        <td>Pera</td>
                        <td>Peric</td>
                        <td>
                            <Button variant="success" type="submit">
                                Accept
                            </Button>
                        </td>
                        <td>
                            <Button variant="danger" type="primary" onClick={handleShow} >
                                Reject
                            </Button>
                        </td>
                        
                        </tr>
                        <tr>
                        <td>2</td>
                        <td>ana@yahoo.com</td>
                        <td>Ana</td>
                        <td>Kuk</td>
                        <td>
                            <Button variant="success" type="submit">
                                Accept
                            </Button>
                        </td>
                        <td>
                            <Button variant="danger" type="submit" onClick={handleShow} >
                                Reject
                            </Button>
                        </td>
                        </tr>
                        <tr>
                        <td>3</td>
                        <td>top@gmail.com</td>
                        <td>Ivo</td>
                        <td>Tot</td>
                        <td>
                            <Button variant="success" type="submit" >
                                Accept
                            </Button>
                        </td>
                        <td>
                            <Button variant="danger" type="submit" onClick={handleShow} >
                                Reject
                                
                            </Button>
                            
                        </td>
                        </tr>
                    </tbody>
                </Table>
                
            </Row>
            
        </Container>
        </>
        
    );
}


export default HolidayAproval;