package ch.uzh.ifi.access.projections;

import ch.uzh.ifi.access.model.Course;
import ch.uzh.ifi.access.model.CourseInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Projection(types = {Course.class})
public interface CourseOverview {

    Long getId();

    String getSlug();

    String getLogo();

    Map<String,CourseInformation> getInformation();

    LocalDateTime getOverrideStart();

    LocalDateTime getOverrideEnd();

    // TODO move to frontend?
    @Value("#{@courseService.calculateCoursePoints(target.assignments, null)}")
    Double getPoints();

    // TODO move to frontend
    @Value("#{@courseService.getMaxPoints(target.slug)}")
    Double getMaxPoints();

    @Value("#{@activityRegistry.getOnlineCount(target.slug)}")
    Long getOnlineCount();


    Long getStudentsCount();


    @Value("#{@courseService.getTeamMembers(target.supervisors)}")
    List<MemberOverview> getSupervisors();

    @Value("#{@courseService.getTeamMembers(target.assistants)}")
    List<MemberOverview> getAssistants();
}