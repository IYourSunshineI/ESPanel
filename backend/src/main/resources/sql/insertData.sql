DELETE
FROM ESP_GROUP
WHERE id < 1;

DELETE
FROM ROOM
WHERE id < 1;

INSERT INTO ROOM (id, title)
VALUES (-1, 'Room 1'),
       (-2, 'Room 2')
;

INSERT INTO ESP_GROUP (id, title, ip_address, state, room_id)
VALUES (-1, 'Group 1', '111.111.111.111', false, -1),
       (-2, 'Group 2', '222.222.222.222', true, -1)