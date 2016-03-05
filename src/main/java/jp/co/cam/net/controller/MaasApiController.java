package jp.co.cam.net.controller;


import jp.co.cam.net.dao.ReservationDao;
import jp.co.cam.net.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MaasApiController {

    @Autowired
    ReservationDao reservationDao;

    @RequestMapping(path = "/")
    List<Reservation> all() {
        return reservationDao.selectAll();
    }

    @RequestMapping(path = "/", params = "name")
    List<Reservation> name(@RequestParam String name) {
        return reservationDao.selectByName(name);
    }

}
