package tests;

import familyTree.controller.FamilyController;
import familyTree.model.FamilyMember;
import familyTree.repository.FamilyData;

import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@WebMvcTest(value = FamilyController.class)
@WithMockUser
public class APITests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FamilyData familyData;

    FamilyMember mockMember = new FamilyMember(101, "Bert", "101-102", "10", "M");

    @Test
    public void GetDetails() throws Exception {

        Mockito.when(familyData.getFamilyMember(Mockito.anyInt())).thenReturn(mockMember);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/FamilyTree/").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{id: 2,name: Sue, familyId: 1-2,familyParentId: ,gender: F}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}

