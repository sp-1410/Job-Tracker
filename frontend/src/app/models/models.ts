export interface Company { id?: number; name: string; website?: string; industry?: string; location?: string; notes?: string; }
export interface Job { id?: number; title: string; location?: string; workMode?: string; jobType?: string; source?: string; url?: string; description?: string; salaryRange?: string; deadline?: string; company: Company; }
export type ApplicationStatus = 'SAVED'|'APPLIED'|'OA'|'SCREENING'|'INTERVIEW'|'OFFER'|'REJECTED'|'WITHDRAWN';
export interface Resume { id?: number; name: string; versionLabel?: string; fileUrl?: string; targetRole?: string; notes?: string; createdAt?: string; }
export interface Application { id?: number; editResumeId?: number; job: Job; status: ApplicationStatus; applicationDate?: string; notes?: string; resume?: Resume; }
export interface ApplicationStatusHistory { id?: number; application: Application; oldStatus?: ApplicationStatus; newStatus: ApplicationStatus; changedAt: string; }
export type OaResult = 'PENDING'|'PASSED'|'FAILED'|'NOT_KNOWN';
export interface OnlineAssessment { id?: number; application: any; platform?: string; assessmentDate?: string; durationMinutes?: number; result: OaResult; score?: number; topics?: string; notes?: string; }
export interface Interview { id?: number; application: any; roundName?: string; interviewType?: string; scheduledAt?: string; meetingLink?: string; status?: string; topics?: string; notes?: string; feedback?: string; preparationChecklist?: string; }
export interface DashboardSummary { totalJobs:number; totalApplications:number; upcomingInterviews:number; applicationsByStatus:Record<string,number>; oaSummary?:Record<string,number>; interviewSummary?:Record<string,number>; upcomingDeadlines?:any[]; upcomingInterviewItems?:any[]; }
