package taxServices.complaint.service;

import taxServices.complaint.entity.Complain;
import yd.itcast.core.service.BaseService;

import java.util.List;
import java.util.Map;

public interface ComplainService extends BaseService<Complain> {
    void autoDealComplain();

    List<Map> findAllByYear(int year);
}
