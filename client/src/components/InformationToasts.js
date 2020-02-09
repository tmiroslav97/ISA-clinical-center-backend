import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { useToasts } from 'react-toast-notifications'
import { errorSelector, successSelector, warnSeletor, infoSelector } from '../store/common/selectors';

const InformationToasts = () => {
    const error = useSelector(errorSelector);
    const success = useSelector(successSelector);
    const warn = useSelector(warnSeletor);
    const info = useSelector(infoSelector);

    const { addToast } = useToasts();

    useEffect(() => {
        if (error != null) {
            addToast(error, {
                appearance: 'error',
                autoDismiss: true,
            });
        }
    }, [error]);

    useEffect(() => {
        if (success != null) {
            addToast(success, {
                appearance: 'success',
                autoDismiss: true,
            });
        }
    }, [success]);

    useEffect(() => {
        if (warn != null) {
            addToast(warn, {
                appearance: 'warning',
                autoDismiss: true,
            });
        }
    }, [warn]);

    useEffect(() => {
        if (info != null) {
            addToast(info, {
                appearance: 'info',
                autoDismiss: true,
            });
        }
    }, [info]);

    return (
        <div id="toastMsg">
        </div>
    );
};

export default InformationToasts;
