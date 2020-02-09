import React, { useEffect } from 'react';
import { history } from '../index';
import { useDispatch, useSelector } from 'react-redux';
import { fetchNurseData } from '../store/user/actions';
import { Spinner, Row, Col, Container, Card, Button } from 'react-bootstrap';
import { userIdSelector, isFetchUserDataSelector } from '../store/user/selectors';

const NurseHomePage = () => {
    const dispatch = useDispatch();
    const id = useSelector(userIdSelector);
    const isFetchUserData = useSelector(isFetchUserDataSelector);

    useEffect(() => {
        if (id != null) {
            dispatch(
                fetchNurseData({
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
                            <Card.Title>Work Calendar</Card.Title>
                            <Card.Text>
                                You can access work calendar.
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/pers/work-cal'); }}>Calendar</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
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
            </Row>
            <Row>
                <Col md={{ span: 3, offset: 1 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>Rewrite prescriptions</Card.Title>
                            <Card.Text>
                                Page for prescription rewrite
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/nurse-page/rew-presc'); }} >Go to</Button>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={{ span: 3 }} xs={12}>
                    <Card style={{ width: '18rem' }}>
                        <Card.Body>
                            <Card.Title>User profile</Card.Title>
                            <Card.Text>
                                You can access your profile
                            </Card.Text>
                            <Button variant="primary" onClick={() => { history.push('/user-prof'); }}>Profile</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}

export default NurseHomePage;