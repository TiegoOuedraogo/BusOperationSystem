-- INSERT INTO nycmta.bus (bus_id, bus_number, capacity)
-- VALUES
--     (1, 'Bus-101', 50),
--     (2, 'Bus-102', 45),
--     (3, 'Bus-103', 40),
--     (4, 'Bus-104', 35),
--     (5, 'Bus-105', 30),
--     (6, 'Bus-106', 25),
--     (7, 'Bus-107', 20),
--     (8, 'Bus-108', 15),
--     (9, 'Bus-109', 10),
--     (10, 'Bus-110', 5)
-- ON CONFLICT  DO NOTHING;
--
--
-- INSERT INTO nycmta.bus_route (route_id, route_name)
-- VALUES (1, 'Route A'),
--                 (2, 'Route B'),
--                 (3, 'Route C'),
--                 (4, 'Route D'),
--                 (5, 'Route E'),
--                 (6, 'Route F'),
--                 (7, 'Route G'),
--                 (8, 'Route H'),
--                 (9, 'Route I'),
--                 (10, 'Route J'),
--                 (11, 'Route K'),
--                 (12, 'Route L'),
--                 (13, 'Route M')
-- ON CONFLICT DO NOTHING;
--
--
-- INSERT INTO nycmta.bus_stop (stop_id, stop_name, location)
-- VALUES
--                 (1, 'Stop 1', 'Location 1'),
--                 (2, 'Stop 2', 'Location 2'),
--                 (3, 'Stop 3', 'Location 3'),
--                 (4, 'Stop 4', 'Location 4'),
--                 (5, 'Stop 5', 'Location 5'),
--                 (6, 'Stop 6', 'Location 6'),
--                 (7, 'Stop 7', 'Location 7'),
--                 (8, 'Stop 8', 'Location 8'),
--                 (9, 'Stop 9', 'Location 9'),
--                 (10, 'Stop 10', 'Location 10')
-- ON CONFLICT DO NOTHING;
--
--
-- INSERT INTO nycmta.route_stop (route_id, stop_order, stop_id)
-- VALUES
--                     (1, 1, 1),
--                     (1, 2, 2),
--                     (2, 1, 2),
--                     (2, 2, 3),
--                     (2, 3, 3),
--                     (2, 4, 4),
--                     (1, 5, 5),
--                     (1, 6, 6),
--                     (1, 7, 7),
--                     (1, 8, 8),
--                     (1, 9, 9)
-- ON CONFLICT DO NOTHING;
--
-- INSERT INTO nycmta.schedules (schedule_id, bus_id, route_id, stop_id, departure_time, arrival_time)
-- VALUES
--     (1, 1, 1, 1, '2024-10-19 08:00:00', '2024-10-19 08:10:00'),
--     (2, 1, 1, 2, '2024-10-19 08:15:00', '2024-10-19 08:25:00'),
--     (3, 2, 2, 2, '2024-10-19 09:00:00', '2024-10-19 09:10:00'),
--     (4, 2, 2, 3, '2024-10-19 09:15:00', '2024-10-19 09:25:00'),
--     (5, 1, 1, 5, '2024-10-19 09:30:00', '2024-10-19 09:30:00'),
--     (6, 1, 1, 6, '2024-10-19 09:45:00', '2024-10-19 09:45:00'),
--     (7, 2, 2, 6, '2024-10-19 10:00:00', '2024-10-19 10:00:00'),
--     (8, 2, 2, 7, '2024-10-19 10:15:00', '2024-10-19 10:15:00'),
--     (9, 1, 1, 8, '2024-10-19 10:30:00', '2024-10-19 10:30:00'),
--     (10, 1, 1, 9, '2024-10-19 10:45:00', '2024-10-19 10:45:00')
-- ON CONFLICT DO NOTHING;
--