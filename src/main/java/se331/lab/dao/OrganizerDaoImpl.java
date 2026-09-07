package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Organizer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder()
                .id(1L)
                .organization("Kat Laydee")
                .address("123 Meow Town")
                .build());
        organizerList.add(Organizer.builder()
                .id(2L)
                .organization("Fern Pollin")
                .address("456 Flora City")
                .build());
        organizerList.add(Organizer.builder()
                .id(3L)
                .organization("Carey Wales")
                .address("789 Playa Del Carmen")
                .build());
        // can add more
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizerList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return organizerList.subList(firstIndex, Math.min(firstIndex + pageSize, organizerList.size()));
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream()
                .filter(organizer -> organizer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}