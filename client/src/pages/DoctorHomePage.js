import React, { useEffect } from 'react';
import { history } from '../index';
import { useDispatch, useSelector } from 'react-redux';
import { Spinner, Card, Button, Col, Row, Container } from 'react-bootstrap';
import { userIdSelector, isFetchUserDataSelector } from '../store/user/selectors';
import { fetchDoctorData } from '../store/user/actions';


const DoctorHomePage = () => {
    const dispatch = useDispatch();
    const id = useSelector(userIdSelector);
    const isFetchUserData = useSelector(isFetchUserDataSelector);

    useEffect(() => {
        if (id != null) {
            dispatch(
                fetchDoctorData({
                    id
                })
            );
        }
    }, [id]);

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
                    <h2 className="border-bottom">Doctor admin profile</h2>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Patient list</Card.Title>
                            <Card.Text>
                                You can see patient list.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/pers/pat-list'); }}>Patients</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Appointment info</Card.Title>
                            <Card.Text>
                                You can see appointments info
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/doc/app-info'); }}>Info</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 4 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Work Calendar</Card.Title>
                            <Card.Text>
                                You can access work calendar.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/pers/work-cal'); }}>Calendar</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Holiday and absence</Card.Title>
                            <Card.Text>
                                You can make holiday or absence requirement
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/pers/hol-abs'); }} >Go to</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Booking</Card.Title>
                            <Card.Text>
                                You can easily book.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/doc/bok-doc'); }}>Search rooms</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Medical reports</Card.Title>
                            <Card.Text>
                                You can edit medical reports
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/doc/med-reps'); }}>Edit</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default DoctorHomePage;