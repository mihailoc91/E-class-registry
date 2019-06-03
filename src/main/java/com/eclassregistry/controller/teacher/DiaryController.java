/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eclassregistry.controller.teacher;

import com.eclassregistry.model.entity.StudentEntity;
import com.eclassregistry.model.entity.SubjectEntity;
import com.eclassregistry.model.entity.TeacherEntity;
import com.eclassregistry.model.repositories.GradesRepository;
import com.eclassregistry.service.impl.GradesServiceImpl;
import com.eclassregistry.service.impl.StudentServiceImpl;
import com.eclassregistry.service.impl.SubjectServiceImpl;
import com.eclassregistry.service.impl.TeacherServiceImpl;
import com.eclassregistry.shared.dto.GradesDto;
import com.eclassregistry.shared.dto.StudentDto;
import com.eclassregistry.shared.dto.SubjectDto;
import com.eclassregistry.shared.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/teacher")
@SessionAttributes("loggedInUser")
public class DiaryController {
    

    
    @Autowired
    GradesRepository gradesRepository;
    
    @Autowired
    GradesServiceImpl gradesServiceImpl;
    
    @Autowired
    SubjectServiceImpl subjectServiceImpl;
    
    @Autowired
    StudentServiceImpl studentServiceImpl;
    
    @Autowired
    
    TeacherServiceImpl teacherServiceImpl;
    
    
            
    @GetMapping("/diary")
    public String diaryPage(ModelMap model, @ModelAttribute("loggedInUser") UserDto loggedInUser){
        //trebace mi dinamicki dobavljati id od ulogovanog ucitelja
       List<StudentDto> allStudents = gradesServiceImpl.getAllStudentsFromTeacher(loggedInUser.getId());
       List<SubjectDto> allSubjects = subjectServiceImpl.getAllSubjectsFromTeacherId(loggedInUser.getId());
       UserDto teacher = teacherServiceImpl.getTeacherById(loggedInUser.getId());
       List<GradesDto> allFinalGrades = new ArrayList<>();
       List<GradesDto> allGrades = new ArrayList<>();
      Integer finalGrade;
       
       GradesDto gradesDtoUspeh = new GradesDto();
       gradesDtoUspeh.setGrades(allGrades);

       
        SubjectDto subject = new SubjectDto();

        for (StudentDto studentDto : allStudents) {
                int studentId = studentDto.getStudentId();
                
                 StudentEntity studentEntity = new StudentEntity();
                 BeanUtils.copyProperties(studentDto, studentEntity);
                 studentEntity.setStudentId(studentId);
                
            for (SubjectDto subjectDto : allSubjects) {
                 int subjectId = subjectDto.getSubjectId();
                 
                 SubjectEntity subjectEntity = new SubjectEntity();
                 BeanUtils.copyProperties(subjectDto, subjectEntity);
                 subjectEntity.setSubjectId(subjectId);
                 
                List<Integer> ocena = gradesRepository.getGradeFromStudentIdAndSubjectID(studentId, subjectId);
                
                if (ocena.isEmpty()) {
                   int mark = 0;
                   GradesDto gradesDto = new GradesDto();
         
                    gradesDto.setGrade(mark);
                    gradesDto.setStudent(studentEntity);
                    gradesDto.setSubject(subjectEntity);
                    
                    allGrades.add(gradesDto);  
                }else{
                    
                    for (Integer integer : ocena) {
              
                    GradesDto gradesDto = new GradesDto();
                    
                        gradesDto.setGrade(integer);
                        gradesDto.setStudent(studentEntity);
                        gradesDto.setSubject(subjectEntity);
                        
                     allGrades.add(gradesDto);  
                     }
                }
            }
        }
        
        for (StudentDto studentDto : allStudents) {
                int studentId = studentDto.getStudentId();
                
                 StudentEntity studentEntity = new StudentEntity();
                 BeanUtils.copyProperties(studentDto, studentEntity);
                 studentEntity.setStudentId(studentId);
                
                
            for (SubjectDto subjectDto : allSubjects) {
                 int subjectId = subjectDto.getSubjectId();
                 
                 SubjectEntity subjectEntity = new SubjectEntity();
                 BeanUtils.copyProperties(subjectDto, subjectEntity);
                 subjectEntity.setSubjectId(subjectId);
                
                finalGrade = gradesRepository.getFinalGradeFromStudentIdAndSubjectID(studentId, subjectId);
       
                
                if (finalGrade == null) {
                   int mark = 0;
                   GradesDto gradesDto = new GradesDto();
         
                    gradesDto.setGrade(mark);
                    gradesDto.setStudent(studentEntity);
                    gradesDto.setSubject(subjectEntity);
                    
                    allFinalGrades.add(gradesDto);  
                }else{
                
                    GradesDto gradesDto = new GradesDto();
                    
                        gradesDto.setGrade(finalGrade);
                        gradesDto.setStudent(studentEntity);
                        gradesDto.setSubject(subjectEntity);
                        
                     allFinalGrades.add(gradesDto);  
                }
            }
        }
        
       
       model.addAttribute("teacher",teacher); 
       model.addAttribute("allFinalGrades",allFinalGrades); 
       model.addAttribute("gradesDtoUspeh",gradesDtoUspeh);
       model.addAttribute("allSubjects",allSubjects);
       model.addAttribute("allStudents",allStudents);

       return "teacher/diary";
    }
    
    
    @PostMapping("/diary")
    public String saveGrade(ModelMap model, @ModelAttribute GradesDto gradesDto, BindingResult result){
        
        if(result.hasErrors()){
            throw new RuntimeException("Wrong input for Grade!");
        }
        
        gradesServiceImpl.saveGrade(gradesDto);
         
        return "redirect:../diary";
    }
   
    
   @GetMapping("/allgrades")
   public String teacherAndGradesPage(ModelMap model,
           @RequestParam(name = "idP", required = true) String id,
           @RequestParam(name = "subject", required = true) String subject,
           @ModelAttribute("loggedInUser") UserDto loggedInUser) {
       
       StudentEntity studentEntity =  studentServiceImpl.findStudentById(Integer.valueOf(id));
       SubjectEntity subjectEntity = subjectServiceImpl.getSubjectById(Integer.valueOf(subject));
       TeacherEntity teacherEntity = teacherServiceImpl.getTeacher(loggedInUser.getId());
       
       GradesDto gradeAdd = new GradesDto();
       GradesDto finalGrade = new GradesDto();
       StudentDto studentDto =  new StudentDto();
       List<GradesDto> allGrades = new ArrayList<>();
    
       List<SubjectDto> allSubjects = subjectServiceImpl.getAllSubjectsFromTeacherId(loggedInUser.getId());
       
       List<GradesDto> grades = gradesServiceImpl.getAllGradesFromStudentIdAndSubjectId(Integer.valueOf(id), Integer.valueOf(subject));

            finalGrade.setFinalGrade(true);
            finalGrade.setStudent(studentEntity);
            finalGrade.setSubject(subjectEntity);
            finalGrade.setTeacher(teacherEntity);
            
            gradeAdd.setFinalGrade(false);
            gradeAdd.setStudent(studentEntity);
            gradeAdd.setSubject(subjectEntity);
            gradeAdd.setTeacher(teacherEntity);
     
       
       for (GradesDto grade : grades) {
           if (grade.getFinalGrade() == true) {
               finalGrade = grade;
           }else{
             allGrades.add(grade);
           }
        }
       
       model.addAttribute("gradeAdd",gradeAdd);
       model.addAttribute("allGrades",allGrades);
       model.addAttribute("studentDto",studentDto);
       model.addAttribute("finalGrade",finalGrade);
       model.addAttribute("allSubjects",allSubjects);
       
       return "teacher/grade";
   }
   
   @PostMapping("/allgrades")
    public String saveGradeFromStudent(ModelMap model,@Valid @ModelAttribute GradesDto gradesDto, BindingResult result){
        
        if(result.hasErrors()){
            throw new RuntimeException("Wrong input for grade!");
        }
        if(gradesDto.getGrade()<=5 && gradesDto.getGrade()>=1){
            gradesServiceImpl.saveGrade(gradesDto);
        }
        
        
         
        return "redirect:../teacher/allgrades?idP="+gradesDto.getStudent().getStudentId()+"&subject="+gradesDto.getSubject().getSubjectId()+"&teacher="+gradesDto.getTeacher().getId();
    }
    
    @GetMapping("/allgrades/delete")
    public String deleteStudent(ModelMap model, @RequestParam(name = "id", required = true) String id){
        GradesDto gradesDto = new GradesDto();
        
        Integer gradeId = Integer.valueOf(id);
        
        if(gradeId !=null){
           gradesDto = gradesServiceImpl.getGradeById(gradeId);
            gradesServiceImpl.deleteGrade(gradeId);
        }
        
    return "redirect:/teacher/allgrades?idP="+gradesDto.getStudent().getStudentId()+"&subject="+gradesDto.getSubject().getSubjectId()+"&teacher="+gradesDto.getTeacher().getId();
    }
}