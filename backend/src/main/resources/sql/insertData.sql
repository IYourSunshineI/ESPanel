DELETE
FROM ROOM
WHERE id < 1;

INSERT INTO ROOM (id, title)
VALUES (-1, 'Room 1'),
       (-2, 'Room 2')
;