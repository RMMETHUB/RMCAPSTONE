package com.capstoneproject.ms7matchservicev1.match;



import java.util.List;

public interface MatchService {

     boolean addMatch(MatchRequestModel matchEntity);
     boolean updateMatch(MatchRequestModel matchEntity);
     boolean deleteMatchById(Long id) ;
     boolean validateMatchById(Long id);
     MatchResponseModel getMatchById(Long id);
     List<MatchResponseModel> getAllMatch();
     MatchResponseForTicketRequestModel getMatchResponseForTicketRequestModel(Long id);


}
