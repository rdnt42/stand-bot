INSERT INTO EQUIPMENT_STATES (id, name) VALUES (1, 'Доступен');
INSERT INTO EQUIPMENT_STATES (id, name) VALUES (2, 'Недоступен');
INSERT INTO EQUIPMENT_STATES (id, name) VALUES (3, 'Сбои в работе');

INSERT INTO STANDS (stand_id, name, stand_status_id, metrics_status_id, dependent_session_status_id, independent_session_status_id) VALUES (1, 'B1', 3, 1, 2, 1);
INSERT INTO STANDS (stand_id, name, stand_status_id, metrics_status_id, dependent_session_status_id, independent_session_status_id) VALUES (2, 'B3', 1, 1, 1, 1);
INSERT INTO STANDS (stand_id, name, stand_status_id, metrics_status_id, dependent_session_status_id, independent_session_status_id) VALUES (3, 'B5', 3, 2, 1, 1);
INSERT INTO STANDS (stand_id, name, stand_status_id, metrics_status_id, dependent_session_status_id, independent_session_status_id) VALUES (4, 'SI1', 2, 2, 2, 2);
INSERT INTO STANDS (stand_id, name, stand_status_id, metrics_status_id, dependent_session_status_id, independent_session_status_id) VALUES (5, 'SI3', 1, 1, 1, 1);