package ru.camoroh13.realtys.service;

import org.springframework.transaction.annotation.Transactional;
import ru.camoroh13.realtys.domain.Image;

public interface ImageService {

    @Transactional
    public void save(Image image);

    @Transactional
    public void delete(Image image);

    @Transactional
    public Image get(Integer id);

    public  void markImage(String srcImg, String markImg, float alpha, int mark_position);
}
