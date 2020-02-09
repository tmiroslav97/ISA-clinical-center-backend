import React from 'react';
import {Container, Col, Row} from 'react-bootstrap';

function MedicalRecord(){
    return(
        <Container>
           
           <Row >
                <Col md={{ span:12 }} xs={12}>
                    <h3 className="border-bottom">Medical Record</h3>
                </Col>
            </Row>
            
        </Container>
    );


}
export default MedicalRecord;