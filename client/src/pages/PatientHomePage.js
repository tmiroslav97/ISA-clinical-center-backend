import React, { useEffect } from 'react';
import { history } from '../index';
import { useDispatch, useSelector } from 'react-redux';
import { Card, Button, Col, Row, Container, Spinner } from 'react-bootstrap';
import { fetchPatientData } from '../store/user/actions';
import { userIdSelector, userDataSelector, isFetchUserDataSelector } from '../store/user/selectors';


const PatientHomePage = () => {
    const dispatch = useDispatch();
    const patientId = useSelector(userIdSelector);
    const isFetchUserData = useSelector(isFetchUserDataSelector);

    useEffect(() => {
        if (patientId != null) {
            dispatch(
                fetchPatientData({
                    patientId
                })
            );
        }
    }, [patientId]);

    if (!isFetchUserData) {
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
                    <h2 className="border-bottom">Patient home page</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Search clinics</Card.Title>
                            <Card.Text>
                                You can search clinics.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/pat/search-cli'); }}>Clinic</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Search doctors</Card.Title>
                            <Card.Text>
                                You can search doctors.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/pat/search-doc'); }}>Doctor</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 4 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>History</Card.Title>
                            <Card.Text>
                                You can see history of surgeries and examinations.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/pat/history'); }}>History</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Medical record</Card.Title>
                            <Card.Text>
                                You can see medical record.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/medical-rec'); }} >Medical record</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Profile</Card.Title>
                            <Card.Text>
                                You can see profile.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/user-prof'); }}>Profile</Button>
                        </Card.Body>
                    </Card>
                </Col>
            
            </Row>
        </Container>
    );
}
 
export default PatientHomePage;