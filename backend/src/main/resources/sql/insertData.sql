DELETE
FROM DIMMER_MODULE;
WHERE id < 0;

DELETE
FROM RGB_MODULE;
WHERE id < 0;

DELETE
FROM KNOB_MODULE;
WHERE id < 0;

DELETE
FROM ESP_GROUP;
WHERE id < 0;

DELETE
FROM ROOM;
WHERE id < 0;

INSERT INTO ROOM (id, title)
VALUES (-1, 'Room 1'),
       (-2, 'Room 2')
;

INSERT INTO ESP_GROUP (id, title, ip_address, state, room_id)
VALUES (-1, 'Group 1', '111.111.111.111', false, -1),
       (-2, 'Group 2', '222.222.222.222', true, -1)
;

INSERT INTO KNOB_MODULE(id, title, pin_number, group_id)
VALUES (-1, 'Dimmer 1', 1, -1),
       (-2, 'Dimmer 2', 2, -2),
       (-3, 'RGB 1', 2, -1),
       (-4, 'RGB 2', 3, -2)
;

INSERT INTO DIMMER_MODULE(id, brightness)
VALUES (-1, 255),
       (-2, 0)
;

INSERT INTO RGB_MODULE(id, color)
VALUES (-3, '#FFFFFF'),
       (-4, '#000000')
;