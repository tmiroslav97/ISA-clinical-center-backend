import React from 'react';
import { Nav, Col, Row, Tab} from 'react-bootstrap';
import UpdateCAprofile from './UpdateCAprofile';
import PasswordChanger from '../PasswordChanger';

const Editing = () => {
    return(
        <Tab.Container id="left-tabs-doc-home" >
            <Row>
            <Col sm={3}>
            <Nav variant="pills" className="flex-column">
                <Nav.Item>
                    <Nav.Link eventKey="fourth">Update profile</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="fifth">Change password</Nav.Link>
                </Nav.Item>
            </Nav>
            </Col>
            <Col sm={9}>
            <Tab.Content>
                <Tab.Pane eventKey="fourth">
                    <UpdateCAprofile/>
                </Tab.Pane>
                <Tab.Pane eventKey="fifth">
                    <PasswordChanger/>
                </Tab.Pane>
                </Tab.Content>
            </Col>
        </Row>
        </Tab.Container>
    );
}

export default Editing;