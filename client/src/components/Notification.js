import React, { useState } from 'react';
import { Toast, Row, Col } from 'react-bootstrap';

const Notification = () => {
  const [show, setShow] = useState(true);
  const notify = type => {
    return () => {
      switch (type) {
        case 'info':
          toast.info('Info message', {
            closeButton: false
          });
          break;
        case 'success':
          toast.success('Success message', {
            closeButton: false
          });
          break;
        case 'warning':
          toast.warn('Warning message', {
            closeButton: false
          });
          break;
        case 'error':
          toast.error('Error message', {
            closeButton: false
          });
          break;
        default:
          toast.error('Error message', {
            closeButton: false
          });
      }
    };
  };

  return(
    <Row>
      <Col xs={6}>
        <Toast onClose={() => setShow(false)} show={show} delay={3000} autohide>
          <Toast.Header>
            <img
              src="holder.js/20x20?text=%20"
              className="rounded mr-2"
              alt=""
            />
            <strong className="mr-auto">Bootstrap</strong>
            <small>11 mins ago</small>
          </Toast.Header>
          <Toast.Body>Woohoo, you're reading this text in a Toast!</Toast.Body>
        </Toast>
      </Col>
    </Row>
  );
};

export default Notification;