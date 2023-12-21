package com.middevs.wasi.usecase;


import com.middevs.wasi.dto.ResponseDTO;
import com.middevs.wasi.repository.SearchDetailJpaRepository;
import com.middevs.wasi.repository.SearchJpaRepository;
import com.middevs.wasi.table.Search;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DeleteSearchUseCase {

    SearchJpaRepository searchJpaRepository;
    SearchDetailJpaRepository searchDetailJpaRepository;

    public ResponseDTO ejecutar(int idSearch) {
        try{
            Integer stateSearchDetail = searchDetailJpaRepository.deleteByIdSearchDetail(idSearch);
            System.out.println(stateSearchDetail);
            Integer stateSearchService = searchJpaRepository.deleteByIdSearchService(idSearch);
            Integer stateSearchFeature = searchJpaRepository.deleteByIdSearchFeature(idSearch);
            Integer stateSearchInterest = searchJpaRepository.deleteByIdSearchInterest(idSearch);
            Integer stateSearch = searchJpaRepository.deleteByIdSearch(idSearch);
            if ((stateSearchDetail==1) && (stateSearchService==1) && (stateSearchFeature==1) && (stateSearch==1) && (stateSearchInterest==1)){
                return new ResponseDTO("success");
            }else{
                return new ResponseDTO("error", "Doesn't exist Search");
            }
        }catch(Exception e){
             System.out.println("Don't exist Search or some query don't work");
            return new ResponseDTO("error", "Don't exist Search or some query don't work");
        }


    }
}
